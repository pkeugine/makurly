import numpy as np


class recommender_explicitMF:
	def __init__(self, max_iters, n_factor, reg_lambda):
		self.max_iters = max_iters
		self.n_factor = n_factor
		self.reg_lambda = reg_lambda

	def opt(self, R, obj_vec, fixed_vecs):
		"""
		when updating the user matrix,
		the item matrix is the fixed vector and vice versa
		"""
		# X가 object, Y가 fixed vector일시
		## A_inv = (Y'Y + lambda * I)^(-1)
		A_inv = np.linalg.inv(fixed_vecs.T.dot(fixed_vecs) + np.eye(self.n_factor) * self.reg_lambda)
		## b = RY
		b = R.dot(fixed_vecs)
		out = b.dot(A_inv);
		out[out < 0] = 0
		return out

	def fit(self, R):  # R should have user_ids in rows, item_ids in columns
		self.R = R
		self.n_user, self.n_item = R.shape

		# initiallzing user_matrix X & item_matrix Y
		self.X = np.random.rand(self.n_user, self.n_factor)
		self.Y = np.random.rand(self.n_item, self.n_factor)

		for n_iter in range(self.max_iters):
			self.X = self.opt(R, self.X, self.Y)
			self.Y = self.opt(R.T, self.Y, self.X)

		# print() loss?
		self.R_hat = self.X.dot(self.Y.T)
		return

	def predict_score(self, user_idx, item_idx):
		if (user_idx in self.R_hat.index) & (item_idx in self.R_hat.columns):
			return self.R_hat.loc[self.R_hat.index == user_idx, item_idx].values[0]
		else:
			return np.nan

	def predict_topN(self, N, user_idx):
		if (user_idx in self.R_hat.index):
			top_N_item_idx = list(self.R_hat.columns[np.array(-self.R_hat[self.R_hat.index == user_idx]).argsort()[0][:N]])
			top_N_item_value = list(self.R_hat[self.R_hat.index == user_idx][top_N_item_idx].values[0])
			return {'top_N_item_id': top_N_item_idx, 'top_N_item_score': top_N_item_value}

		else:
			return {'top_N_item_id': [np.nan], 'top_N_item_score': [np.nan]}


class recommender_implicitMF:
	def __init__(self, max_iters, n_factor, alpha, reg_lambda):
		self.max_iters = max_iters
		self.n_factor = n_factor
		self.alpha = alpha
		self.reg_lambda = reg_lambda

	def opt(self, C, P, X, Y):
		"""
		when updating the user matrix,
		the item matrix is the fixed vector and vice versa
		"""
		# update X
		for u in range(self.n_user):
			# print('n_user : ' + str(u))
			Cu = np.diag(C[u, :])

			A_inv = np.linalg.inv((Y.T @ Cu @ Y) + self.reg_lambda * np.eye(self.n_factor))
			b = Y.T @ Cu @ P[u, :]
			X[u] = A_inv @ b
			X[u][X[u] < 0] = 0

		# update Y
		for i in range(self.n_item):
			# print('n_item : ' + str(i))
			Ci = np.diag(C[:, i])

			A_inv = np.linalg.inv((X.T @ Ci @ X) + self.reg_lambda * np.eye(self.n_factor))
			b = X.T @ Ci @ P[:, i]
			Y[i] = A_inv @ b
			Y[i][Y[i] < 0] = 0

		return X, Y

	def fit(self, R):  # R should have user_ids in rows, item_ids in columns
		self.R = R
		self.n_user, self.n_item = R.shape

		# assign P(preference) matrix
		P = R.copy();
		P[P > 0] = 1
		self.P = P

		# assign C(confidence) matrix
		C = 1 + self.alpha * self.R
		self.C = C

		# initiallzing user_matrix X & item_matrix Y
		self.X = np.random.rand(self.n_user, self.n_factor)
		self.Y = np.random.rand(self.n_item, self.n_factor)

		##########
		# to matrix & store user_list and item_list for later
		self.user_list = list(R.index)
		self.item_list = list(R.columns)

		self.R = np.array(R)
		self.P = np.array(P)
		self.C = np.array(C)

		##########
		# optimization
		for n_iter in range(self.max_iters):
			print('n_iter ' + str(n_iter))
			self.X, self.Y = self.opt(C=self.C, P=self.P, X=self.X, Y=self.Y)

		# P calc & P row, column 붙이기
		self.P_hat = pd.DataFrame(self.X.dot(self.Y.T))
		self.P_hat.index = self.user_list
		self.P_hat.columns = self.item_list

		return

	def predict_score(self, user_idx, item_idx):
		if (user_idx in self.P_hat.index) & (item_idx in self.P_hat.columns):
			return self.P_hat.loc[self.P_hat.index == user_idx, item_idx].values[0]
		else:
			return np.nan

	def predict_topN(self, N, user_idx):
		if (user_idx in self.P_hat.index):
			top_N_item_idx = list(self.P_hat.columns[np.array(-self.P_hat[self.P_hat.index == user_idx]).argsort()[0][:N]])
			top_N_item_value = list(self.P_hat[self.P_hat.index == user_idx][top_N_item_idx].values[0])
			return {'top_N_item_id': top_N_item_idx, 'top_N_item_score': top_N_item_value}

		else:
			return {'top_N_item_id': [np.nan], 'top_N_item_score': [np.nan]}


class explicitMF_recommender:
	def __init__(self, max_iters, n_factor, reg_lambda):
		self.max_iters = max_iters
		self.n_factor = n_factor
		self.reg_lambda = reg_lambda

	def opt(self, R, obj_vec, fixed_vecs):
		"""
		when updating the user matrix,
		the item matrix is the fixed vector and vice versa
		"""
		# X가 object, Y가 fixed vector일시
		## A_inv = (Y'Y + lambda * I)^(-1)
		A_inv = np.linalg.inv(fixed_vecs.T.dot(fixed_vecs) + np.eye(self.n_factor) * self.reg_lambda)
		## b = RY
		b = R.dot(fixed_vecs)

		return b.dot(A_inv)

	def fit(self, R):  # R should have user_ids in rows, item_ids in columns
		self.R = R
		self.n_user, self.n_item = R.shape

		# initiallzing user_matrix X & item_matrix Y
		self.X = np.random.rand(self.n_user, self.n_factor)
		self.Y = np.random.rand(self.n_item, self.n_factor)

		for n_iter in range(self.max_iters):
			self.X = self.opt(R, self.X, self.Y)
			self.Y = self.opt(R.T, self.Y, self.X)

		# print() loss?
		self.R_hat = self.X.dot(self.Y.T)
		return

	def predict_score(self, user_idx, item_idx):
		return self.R_hat[user_idx, item_idx]

	def predict_topN(self, N, user_idx):
		top_N_item_idx = (-self.R_hat[user_idx, :]).argsort()[:N]
		top_N_item_score = self.R_hat[user_idx, top_N_item_idx]
		return {'item': top_N_item_idx, 'score': top_N_item_score}


class implicitMF_recommender:
	def __init__(self, max_iters, n_factor, alpha, reg_lambda):
		self.max_iters = max_iters
		self.n_factor = n_factor
		self.alpha = alpha
		self.reg_lambda = reg_lambda

	def opt(self, C, P, X, Y):
		"""
		when updating the user matrix,
		the item matrix is the fixed vector and vice versa
		"""
		# update X
		for u in range(self.n_user):
			print('n_user : ' + str(u))
			Cu = np.diag(C[u, :])

			A_inv = np.linalg.inv((Y.T @ Cu @ Y) + self.reg_lambda * np.eye(self.n_factor))
			b = Y.T @ Cu @ P[u, :]
			X[u] = A_inv @ b

		# update Y
		for i in range(self.n_item):
			print('n_item : ' + str(i))
			Ci = np.diag(C[:, i])

			A_inv = np.linalg.inv((X.T @ Ci @ X) + self.reg_lambda * np.eye(self.n_factor))
			b = X.T @ Ci @ P[:, i]
			Y[i] = A_inv @ b

		return X, Y

	def fit(self, R):  # R should have user_ids in rows, item_ids in columns
		self.R = R
		self.n_user, self.n_item = R.shape

		# assign P(preference) matrix
		P = R.copy();
		P[P > 0] = 1
		self.P = P

		# assign C(confidence) matrix
		C = 1 + self.alpha * self.R
		self.C = C

		# initiallzing user_matrix X & item_matrix Y
		self.X = np.random.rand(self.n_user, self.n_factor)
		self.Y = np.random.rand(self.n_item, self.n_factor)

		# optimization
		for n_iter in range(self.max_iters):
			print('n_iter ' + str(n_iter))
			self.X, self.Y = self.opt(C=self.C, P=self.P, X=self.X, Y=self.Y)

		# print() loss?
		self.P_hat = self.X.dot(self.Y.T)
		return

	def predict_score(self, user_idx, item_idx):
		return self.P_hat[user_idx, item_idx]

	def predict_topN(self, N, user_idx):
		top_N_item_idx = (-self.P_hat[user_idx, :]).argsort()[:N]
		top_N_item_score = self.P_hat[user_idx, top_N_item_idx]
		return {'item': top_N_item_idx, 'score': top_N_item_score}


class recommender_ens_linucb:
	def __init__(self, models, I, U, N=5, alpha=10):  #

		# alpha : tunning hyperparams(explore-exploit)
		self.alpha = alpha

		# I, U, L, N
		self.I = I  # number of items (fixed for now)
		self.U = U  # number of users (fixed for now)
		self.L = len(models)  # number of models to ensemble
		self.N = N

		# list of models
		self.models = models

		# initiallizing matrices
		self.A = np.array([np.identity(self.L)] * I)
		self.A_inv = np.array([np.identity(self.L)] * I)
		self.b = np.zeros((I, self.L, 1))
		self.theta_hat = self.A_inv @ self.b

	# fit method for pretraining -- Here, assume N(default 5) items are recommoended to customer.
	## If, customer buys any of recommended item, gets credit
	def fit(self, interaction, interaction_item):  # piece(row or rows) of interaction
		# sort by datetime and reset_index
		interaction = interaction.sort_values('order_date').reset_index(drop=True)

		# for each row(t) of interactions :
		for t in range(interaction.shape[0]):

			interact_id = interaction.loc[t, 'id']  # interaction id at t-th interaction
			u_t = interaction.loc[t, 'customer_id'];  # user index u at t-th interaction

			# context(here, predicted scores from other RS algorithms pretrained)
			X_u = np.array([[self.models[l].predict_score(user_idx=u_t, item_idx=i_t) for l in range(self.L)] for i_t in
											range(1, self.I + 1)])
			X_u = np.nan_to_num(X_u, 0)  # If nan -> 0 ( That is no recoomendation is provided.)

			# r_t : reward at t-th interaction
			## of course, reward is observed
			r_t = np.zeros(self.I)

			i_item = interaction_item[interaction_item.interaction_id == interact_id].item_id
			q_item = interaction_item[interaction_item.interaction_id == interact_id].quantity

			for i, q in zip(i_item, q_item):
				r_t[i - 1] = q  # i-1, because python starts indexing with 0.

			# calculating ucb_I (ucb for all items)
			ucb_I = []
			for i in range(self.I):
				A_i = self.A[i];
				A_inv_i = self.A_inv[i];
				b_i = self.b[i];
				x_i = X_u[i]
				theta_hat_i = A_inv_i @ b_i

				ucb_i = (np.transpose(theta_hat_i) @ x_i + self.alpha * np.sqrt(np.transpose(x_i) @ A_inv_i @ x_i))[0]
				ucb_I.append(ucb_i)

			ucb_I = np.array(ucb_I)

			# update A, A_inv, b, theta_hat
			N = self.N
			update_i_idx = (-ucb_I).argsort()[:N]

			for i in update_i_idx:
				self.A[i] = self.A[i] + X_u[i].reshape(self.L, 1) @ np.transpose(X_u[i].reshape(self.L, 1))
				self.A_inv[i] = np.linalg.inv(self.A[i])
				self.b[i] = self.b[i] + r_t[i] * X_u[i].reshape(self.L, 1)
				self.theta_hat[i] = self.A_inv[i] @ self.b[i]

		return

	def predict_score(self, user_idx, item_idx):

		X_u = np.array([self.models[l].predict_score(user_idx=user_idx, item_idx=item_idx) for l in range(self.L)])
		X_u = np.nan_to_num(X_u, 0)  # If nan -> 0 ( That is no recoomendation is provided.)

		u_t = user_idx - 1;
		i_t = item_idx - 1
		A_i = self.A[i_t];
		A_inv_i = self.A_inv[i_t];
		b_i = self.b[i_t]
		theta_hat_i = A_inv_i @ b_i

		ucb_i = (np.transpose(theta_hat_i) @ X_u + self.alpha * np.sqrt(np.transpose(X_u) @ A_inv_i @ X_u))[0]

		return ucb_i

	def predict_topN(self, user_idx, N=5):
		# context(here, predicted scores from other RS algorithms pretrained)
		X_u = np.array([[self.models[l].predict_score(user_idx=user_idx, item_idx=i_t) for l in range(self.L)] for i_t in
										range(1, self.I + 1)])
		X_u = np.nan_to_num(X_u, 0)  # If nan -> 0 ( That is no recoomendation is provided.)

		# calculating ucb_I (ucb for all items)
		ucb_I = []
		for i in range(self.I):
			A_i = self.A[i];
			A_inv_i = self.A_inv[i];
			b_i = self.b[i]

			x_i = X_u[i]
			theta_hat_i = A_inv_i @ b_i

			theta_hat_i = A_inv_i @ b_i
			ucb_i = (np.transpose(theta_hat_i) @ x_i + self.alpha * np.sqrt(np.transpose(x_i) @ A_inv_i @ x_i))[0]
			ucb_I.append(ucb_i)

		ucb_I = np.array(ucb_I)

		N = self.N
		update_i_idx = (-ucb_I).argsort()[:N]
		update_i = update_i_idx + 1

		return {'top_N_item_id': list(update_i), 'top_N_item_score': list(ucb_I[update_i_idx])}

	# predict(RS 제공) & 업데이트
	def predict_topN_2(self, interaction, interaction_item, N=None):
		N = self.N

		# sort by datetime and reset_index
		interaction = interaction.sort_values('order_date').reset_index(drop=True)

		out_topN = []
		# for each row(t) of interactions :
		for t in range(interaction.shape[0]):
			interact_id = interaction.loc[t, 'id']  # interaction id at t-th interaction
			u_t = interaction.loc[t, 'customer_id'];  # user index u at t-th interaction

			######### PREDICT PART #########
			# predict
			# context(here, predicted scores from other RS algorithms pretrained)
			X_u = np.array([[self.models[l].predict_score(user_idx=u_t, item_idx=i_t) for l in range(self.L)] for i_t in
											range(1, self.I + 1)])
			# If nan -> 0 ( That is no recoomendation is provided.)
			X_u = np.nan_to_num(X_u, 0)

			# calculating ucb_I (ucb for all items)
			ucb_I = []
			for i in range(self.I):
				A_i = self.A[i];
				A_inv_i = self.A_inv[i];
				b_i = self.b[i]

				x_i = X_u[i]
				theta_hat_i = A_inv_i @ b_i

				theta_hat_i = A_inv_i @ b_i
				ucb_i = (np.transpose(theta_hat_i) @ x_i + self.alpha * np.sqrt(np.transpose(x_i) @ A_inv_i @ x_i))[0]
				ucb_I.append(ucb_i)

			ucb_I = np.array(ucb_I)

			update_i_idx = (-ucb_I).argsort()[:N]
			update_i = update_i_idx + 1
			out_topN.append({'interaction_id': interact_id,
											 'top_N_item_id': list(update_i),
											 'top_N_item_score': list(ucb_I[update_i_idx])})
			######### ######### ######### #########
			######### UPDATE PART #########

			# r_t : reward at t-th interaction
			## of course, reward is observed
			r_t = np.zeros(self.I)

			i_item = interaction_item[interaction_item.interaction_id == interact_id].item_id
			q_item = interaction_item[interaction_item.interaction_id == interact_id].quantity

			for i, q in zip(i_item, q_item):
				r_t[i - 1] = q  # i-1, because python starts indexing with 0.

			# update A, A_inv, b, theta_hat
			update_i_idx = (-ucb_I).argsort()[:N]

			for i in update_i_idx:
				self.A[i] = self.A[i] + X_u[i].reshape(self.L, 1) @ np.transpose(X_u[i].reshape(self.L, 1))
				self.A_inv[i] = np.linalg.inv(self.A[i])
				self.b[i] = self.b[i] + r_t[i] * X_u[i].reshape(self.L, 1)
				self.theta_hat[i] = self.A_inv[i] @ self.b[i]

		return out_topN