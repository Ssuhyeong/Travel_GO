import axios from "@/service/axios";

export const deleteBoard = (type, no, test) => axios.delete(`/${type}/${no}`);
