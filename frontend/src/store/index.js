import { createStore } from "vuex";

export default createStore({
  state: {
    toastShow: false,
    text: "",
    toastcolor: "",
  },
  mutations: {
    setShow(state, value) {
      state.toastShow = value;
    },
    setText(state, value) {
      state.text = value;
    },
    setColor(state, value) {
      state.toastcolor = value;
    },
  },
});
