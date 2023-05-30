import {createRouter, createWebHistory} from 'vue-router';
import Home from '@/pages/MainHome';
import Login from '@/pages/MainVue';

const routes = [
    {path:'/login', component:Login}, //root 경로로 들어올때는 component를 home으로 연결해라
    {path:'/', component:Home}
]

const router = createRouter({
    history : createWebHistory(),
    routes
})

export default router;