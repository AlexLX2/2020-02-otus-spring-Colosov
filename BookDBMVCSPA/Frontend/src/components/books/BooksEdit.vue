<template>
    <div v-if="currentBook" class="edit-form">
        <h4>Book</h4>
        <form>
            <div class="form-group">
                <label for="title">Title</label>
                <input type="text" class="form-control" id="title" v-model="currentBook.title"/>
            </div>
            <div class="form-group">
                <label for="author">Author</label>
                <input type="text" class="form-control" id="author" v-model="currentBook.authorName"/>
            </div>
            <div class="form-group">
                <label for="genre">Genre</label>
                <input type="text" class="form-control" id="genre" v-model="currentBook.genreName"/>
            </div>
            <div class="form-group">
                <label for="id">Id</label>
                <input type="text" class="form-control" id="id" v-model="currentBook.id"/>
            </div>
        </form>
        <button type="submit" class="btn btn-success" @click="updateBook">Update</button>
        <button class="btn badge-danger mr-2" @click="deleteBook">Delete</button>
    </div>
    <div v-else>
        <br/>
        <p>Please click on an Book...</p>
    </div>
</template>

<script>
    import BookDataService from "@/services/BookDataService";

    export default {
        name: "BookEdit",
        data() {
            return {
                currentBook: null
            };
        },
        methods: {
            getBook(id) {
                BookDataService.get(id)
                    .then(response => {
                        this.currentBook = response.data;
                        console.log(response.data);
                    })
                    .catch(e => {
                        console.log(e);
                    })
            },
            updateBook() {
                BookDataService.update(this.currentBook)
                    .then(response => {
                        console.log(response.data);
                    })
                    .catch(e => {
                        console.log(e);
                    });
                this.$router.go(-1);
            },
            deleteBook() {
                BookDataService.delete(this.currentBook.id)
                    .then(response => {
                        console.log(response.data);
                    })
                    .catch(e => {
                        console.log(e.message);
                    });
                this.$router.push("/Books/");
            }
        },
        mounted() {
            this.getBook(this.$route.params.id);
        }
    };
</script>

<style>
    .edit-form {
        max-width: 300px;
        margin: auto;
    }
</style>
