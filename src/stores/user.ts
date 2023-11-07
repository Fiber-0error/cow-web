import { defineStore } from "pinia";


export const useUserStore = defineStore('user', {
  state: () => {
    return {
      token: localStorage.getItem('token'),
    }
  },
  actions: {
    setToken(value: string) {
      this.token = value;
    }
  },
  getters: {
    isLogin: (state) => !!state.token,
  }
})
