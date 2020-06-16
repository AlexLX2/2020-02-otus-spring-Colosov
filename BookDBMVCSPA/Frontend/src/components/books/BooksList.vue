<template>
    <div class="list row">
        <div class="col-md-6">
            <h4>Books List</h4>
            <ul class="list-group">
                <li class="list-group-item" :class="{ active: id == currentId}"
                    v-for="(book, id) in books"
                    :key="id"
                    @click="setActiveBook(book, id)">
                    <strong>{{ book.authorName}}</strong> - {{ book.title }} | <i>{{ book.genreName}}</i>
                </li>
            </ul>
            <div align="center">
                <button class="btn btn-success" @click="createBook">
                    Add new
                </button>
            </div>
        </div>
        <div class="col-md-6">
            <div v-if="currentBook">
                <h4>Book</h4>
                <div>
                    <label><strong>Title: </strong></label> {{ currentBook.title }}
                </div>
                <div>
                    <label><strong>Genre: </strong></label> {{ currentBook.genreName }}
                </div>
                <div>
                    <label><strong>Author: </strong></label> {{ currentBook.authorName }}
                </div>
                <div>
                    <label><strong>ID: </strong></label> {{ currentBook.id }}
                </div>
                <button @click="editBook" class="btn btn-success">Edit</button>
            </div>
            <div v-else>
                <br/>
                <p>Please click on book...</p>
            </div>
        </div>
        <div class="col-md-8">
            <div v-if="currentBook">
                <h4>Comments:</h4>
                <ul class="list-group">
                    <li class="list-group-item" :class="{ active: comment.id == currentCommentId}"
                        v-for="comment in comments"
                        :key="comment.id"
                        @click="setActiveComment(comment, comment.id)">
                        <strong>{{ comment.text }}</strong>
                    </li>
                </ul>
            </div>
            <div align="center">
                <button v-if="currentBook" class="btn btn-success" @click="createComment">
                    Add new
                </button> &nbsp;
                <button v-if="currentComment" @click="editComment" class="btn btn-success">Edit</button>
            </div>
        </div>
    </div>
</template>

<script>
    import router from "@/router";
    import BookDataService from "@/services/BookDataService";
    import CommentDataService from "@/services/CommentDataService";

    export default {
        name: "BooksList",
        data() {
            return {
                books: [],
                currentBook: null,
                currentId: -1,
                title: "",
                comments: [],
                currentComment: null,
                currentCommentId: -1
            };
        },
        methods: {
            retreiveBooks() {
                BookDataService.getAll()
                    .then(response => {
                        this.books = response.data;
                        console.log(response.data);
                    })
                    .catch(e => {
                        console.log(e);
                    });
            },
            setActiveBook(book, id) {
                this.currentBook = book;
                this.currentId = id;
                this.currentComment = null;
                this.currentCommentId = -1;
                CommentDataService.getAllComments(this.currentBook.id)
                    .then(response => {
                        this.comments = response.data;
                        console.log(response.data);
                    })
                    .catch(e => {
                        console.log(e);
                    })
            },
            createBook() {
                router.push("/books/add");
            },
            editBook() {
                router.push("/books/" + this.currentBook.id);
            },
            setActiveComment(comment, id) {
                this.currentComment = comment;
                this.currentCommentId = id;
            },
            editComment() {
                router.push("/books/" + this.currentBook.id + "/comments/" + this.currentCommentId);
            },
            createComment() {
                router.push("/books/" + this.currentBook.id + "/comments/add");
            }
        },
        mounted() {
            this.retreiveBooks();
        }
    }
</script>

<style>
    .list {
        text-align: left;
        max-width: 1000px;
        margin: auto;
    }
</style>
