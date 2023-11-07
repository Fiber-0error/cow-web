import { defineStore } from "pinia";


export const useUserStore = defineStore('user', {
  state: () => {
    return {
      id:localStorage.getItem('id'),
      token: localStorage.getItem('token'),
    }
  },
  actions: {
    setToken(value: string) {
      this.token = value;
    },
    setId(value: string) {
      this.id = value;
    }
  },
  getters: {
    isLogin: (state) => !!state.token,
    getUserId: (state) => state.id,
  }
})
