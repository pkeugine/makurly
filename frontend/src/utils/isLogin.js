const isLogin = () => {
  return JSON.parse(window.localStorage.getItem("isLogin"));
};
export default isLogin;
