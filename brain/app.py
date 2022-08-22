from flask import Flask, request
import pickle

app = Flask(__name__)


class CustomUnpickler(pickle.Unpickler):

	def find_class(self, module, name):
		if name == 'explicitMF_recommender':
			from recommender import explicitMF_recommender
			return explicitMF_recommender
		if name == 'implicitMF_recommender':
			from recommender import implicitMF_recommender
			return implicitMF_recommender
		if name == 'recommender_explicitMF':
			from recommender import recommender_explicitMF
			return recommender_explicitMF
		if name == 'recommender_implicitMF':
			from recommender import recommender_implicitMF
			return recommender_implicitMF
		return super().find_class(module, name)


explicitMF = CustomUnpickler(open('explicitMF.pkl', 'rb')).load()
implicitMF = CustomUnpickler(open('implicitMF.pkl', 'rb')).load()
exMF = CustomUnpickler(open('exMF.pkl', 'rb')).load()
imMF = CustomUnpickler(open('imMF.pkl', 'rb')).load()


@app.route('/')
def hello_world():  # put application's code here
	return 'Hello World!'


@app.route('/ex-recommend', methods=['POST'])
def recommend_explicit():
	request_body = request.get_json()
	user = request_body['user_idx']
	interaction_idx = request_body['interaction_idx']
	result = exMF.predict_topN(N=5, user_idx=user)
	response = {
		"user_idx": user,
		"items": result["top_N_item_id"],
		"interaction_idx": interaction_idx
	}
	return response


@app.route('/im-recommend', methods=['POST'])
def recommend_implicit():
	request_body = request.get_json()
	user = request_body['user_idx']
	interaction_idx = request_body['interaction_idx']
	result = imMF.predict_topN(N=5, user_idx=user)
	response = {
		"user_idx": user,
		"items": result["top_N_item_id"],
		"interaction_idx": interaction_idx
	}
	return response



@app.route('/explicit-recommend', methods=['POST'])
def explicit_recommend():
	request_body = request.get_json()
	user = request_body['user_idx']
	item = request_body['item_idx']
	result = explicitMF.predict_score(user_idx=user, item_idx=item)
	response = {
		"score": result
	}
	return response


@app.route('/explicit-recommend-topn', methods=['POST'])
def explicit_recommend_topn():
	request_body = request.get_json()
	n = request_body['n']
	user = request_body['user_idx']
	result = explicitMF.predict_topN(N=n, user_idx=user)
	response = {
		"items": result["item"].tolist(),
		"scores": result["score"].tolist()
	}
	return response


@app.route('/explicit-recommend-list', methods=['POST'])
def explicit_recommend_list():
	request_body = request.get_json()
	user = request_body['user_idx']
	items = request_body['item_idx']
	result = []
	for item in items:
		result.append(explicitMF.predict_score(user_idx=user, item_idx=item))
	response = {
		"scores": result
	}
	return response


@app.route('/implicit-recommend', methods=['POST'])
def implicit_recommend():
	request_body = request.get_json()
	user = request_body['user_idx']
	item = request_body['item_idx']
	result = implicitMF.predict_score(user_idx=user, item_idx=item)
	response = {
		"score": result
	}
	return response


@app.route('/implicit-recommend-topn', methods=['POST'])
def implicit_recommend_topn():
	request_body = request.get_json()
	n = request_body['n']
	user = request_body['user_idx']
	result = implicitMF.predict_topN(N=n, user_idx=user)
	response = {
		"items": result["item"].tolist(),
		"scores": result["score"].tolist()
	}
	return response


@app.route('/implicit-recommend-list', methods=['POST'])
def implicit_recommend_list():
	request_body = request.get_json()
	user = request_body['user_idx']
	items = request_body['item_idx']
	result = []
	for item in items:
		result.append(implicitMF.predict_score(user_idx=user, item_idx=item))
	response = {
		"scores": result
	}
	return response


if __name__ == '__main__':
	app.run()
