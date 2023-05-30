import { createStore } from 'vuex'

// Create a new store instance.
const store = createStore({
  state () {
    return {
      account:{
        accessToken : "",
        //refreshtoken : ""
      }
    }
  },
  mutations: { //state를 변경시키는 역할. mutation을 통해서만 state를 변경. commit('함수명','전달인자')로 실행시킴. 
    setAccount(state, payload){//payload=id받을변수
      state.account.accessToken = payload;
    }
  }
})

export default store;
