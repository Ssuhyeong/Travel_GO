import { createRouter, createWebHistory } from "vue-router";

import mainView from "@/views/mainView";
import loginView from "@/views/user/loginView";
import profileView from "@/views/user/profileView";
import detailView from "@/views/page/detailView";
import categoryView from "@/views/page/categoryView";
import mapView from "@/views/page/mapView";
import testView from "@/views/testView";
import boardView from "@/views/board/boardView";
import boardWritePage from "@/views/board/boardWriteView";
import boardContentPage from "@/views/board/boardContentView";

const router = createRouter({
  history: createWebHistory(),
  routes: [
    { path: "/", name: "mapView", component: mapView },
    {
      path: "/boardpage",
      name: "boardView",
      component: boardView,
    },
    {
      path: "/boardwritepage",
      name: "boardwritepage",
      component: boardWritePage,
      props: true,
    },
    {
      path: "/boardcontentpage",
      name: "boardContentPage",
      component: boardContentPage,
      props: true,
    },
    { path: "/testpage", name: "testView", component: testView },
    { path: "/mainpage", name: "mainView", component: mainView },
    { path: "/loginpage", name: "loginpage", component: loginView },
    { path: "/profilepage", name: "profilepage", component: profileView },
    { path: "/detailpage", name: "detailpage", component: detailView },
    { path: "/categorypage", name: "categorypage", component: categoryView },
  ],
});

export default router;
