import Vue from "vue";
import Router from "vue-router";

Vue.use(Router);

export default new Router({
    mode: "history",
    base: process.env.BASE_URL,
    routes: [
        {
            path: "/",
            component: () => import("./components/Home")
        },
        {
            path: "/authors/",
            component: () => import("./components/authors/AuthorsList")
        },
        {
            path: "/authors/add",
            component: () => import("./components/authors/AuthorsAdd")
        },
        {
            path: "/authors/:id",
            component: () => import("./components/authors/AuthorsEdit")
        },
        {
            path: "/genres",
            component: () => import("./components/genres/GenresList")
        },
        {
            path: "/genres/add",
            component: () => import("./components/genres/GenresAdd")
        },
        {
            path: "/genres/:id",
            component: () => import("./components/genres/GenresEdit")
        },
        {
            path: "/books/",
            component: () => import("./components/books/BooksList")
        },
        {
            path: "/books/add",
            component: () => import("./components/books/BooksAdd")
        },
        {
            path: "/books/:id",
            component: () => import("./components/books/BooksEdit")
        },
        {
            path: "/books/:id/comments/add",
            component: () => import("./components/comments/CommentsAdd")
        },
        {
            path: "/books/:id/comments/:cid",
            component: () => import("./components/comments/CommentsEdit")
        },
    ]
})
