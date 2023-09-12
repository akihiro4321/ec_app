export default {
  async login(context, payload) {
    return context.dispatch('auth', {
      ...payload,
      mode: 'login',
    });
  },
  async signup(context, payload) {
    return context.dispatch('auth', {
      ...payload,
      mode: 'signup',
    });
  },
  async auth(context, payload) {
    const mode = payload.mode;
    let url = '/api/auth/authenticate';
    if (mode === 'signup') {
      url = '/api/auth/register';
    }

    const response = await window.axios.post(url, {
      email: payload.email,
      password: payload.password,
      firstName: payload.firstName, // signup only
      lastName: payload.lastName, // signup only
    });

    if (!(response < 300)) {
      // TODO handle error ...
    }
    const responseData = response.data;
    const token = responseData.access_token;
    localStorage.setItem('token', token);
    context.commit('setToken', token);
  },
  async logout(context) {
    await window.axios.get('/api/auth/logout');
    localStorage.removeItem('token');
    context.commit('setToken', null);
  },
};
