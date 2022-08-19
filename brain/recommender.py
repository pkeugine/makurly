import numpy as np

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