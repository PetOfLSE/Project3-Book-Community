import { createWebHistory, createRouter } from "vue-router";
import HomeComponent from "@/components/HomeComponent.vue";
import LoginComponent from "@/components/LoginComponent.vue";
import RegisterComponent from "@/components/RegisterComponent.vue";
import BookListComponent from "../components/BookListComponent.vue"
import BookAddComponent from "@/components/BookAddComponent.vue";
import OauthCallback from "../components/OauthCallback.vue";

const routes = [
    {
        path: "/",
        name: "HomeComponent",
        component: HomeComponent,
    },
    {
        path:"/register",
        name: "RegisterComponent",
        component:RegisterComponent
    },
    {
        path:"/login",
        name: "LoginComponent",
        component: LoginComponent
    },
    {
        path:'/book-list',
        name:"BookListComponent",
        component: BookListComponent
    },
    {
        path:'/book-add',
        name: "BookAddComponent",
        component: BookAddComponent
    },
    {
        path:"/oauth2/callback",
        component: OauthCallback
    }

];

const router = createRouter({
    history: createWebHistory(),
    routes: routes, 
});

export default router;
