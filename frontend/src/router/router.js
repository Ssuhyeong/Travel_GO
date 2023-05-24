import { createRouter, createWebHistory } from "vue-router";
import VueCookies from "vue-cookies";
import swal from "sweetalert";

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
import reviewView from "@/views/page/reviewView";
import tripScheduleView from "@/views/page/tripScheduleView";
import updateScheduleView from "@/views/page/updateScheduleView";

const router = createRouter({
  history: createWebHistory(),
  routes: [
    { path: "/", name: "mapView", component: mapView },
    {
      path: "/boardpage/:type",
      name: "boardView",
      component: boardView,
    },
    {
      path: "/boardwritepage/:type/:articleNo",
      name: "boardwritepage",
      component: boardWritePage,
    },
    {
      path: "/boardcontentpage/:type/:articleNo",
      name: "boardcontentpage",
      component: boardContentPage,
    },
    {
      path: "/testpage",
      name: "testView",
      component: testView,
    },
    { path: "/mainpage", name: "mainView", component: mainView },
    {
      path: "/loginpage",
      name: "loginpage",
      component: loginView,
      meta: { authRequired: true },
    },
    { path: "/profilepage", name: "profilepage", component: profileView },
    {
      path: "/detailpage/:contentId",
      name: "detailpage",
      component: detailView,
    },
    { path: "/categorypage", name: "categorypage", component: categoryView },
    {
      path: "/reviewpage/:contentId/:title",
      name: "reviewpage",
      component: reviewView,
    },
    {
      path: "/tripScheduleView/:title/:user/:scheduleInfo",
      name: "tripScheduleView",
      component: tripScheduleView,
    },
    {
      path: "/updateScheduleView/:title/:scheduleInfo",
      name: "updateScheduleView",
      component: updateScheduleView,
    },
  ],
  scrollBehavior() {
    return { top: 0 };
  },
});

router.beforeEach(async (to, from, next) => {
  // if (
  //   VueCookies.get("accessToken") === null &&
  //   VueCookies.get("refreshToken") !== null
  // ) {
  //   // await refreshToken();
  // }

  if (
    to.matched.some(function (routeInfo) {
      return routeInfo.meta.authRequired;
    }) ||
    VueCookies.get("accessToken")
  ) {
    return next();
  }

  swal("로그인이 필요한 페이지입니다. ", "로그인을 해주세요");
  return next("/loginpage");
});
export default router;
