<template>
    <div class="submit-form">
        <div class="form-group">
            <label for="name">Book title:</label>
            <input type="text" class="form-control" id="name" required v-model="book.title" name="title"/>
        </div>
        <div class="form-group">
            <label for="authorName">Author:</label>
            <input class="form-control" id="authorName" required v-model="book.authorName" name="authorName">
        </div>
        <div class="form-group">
            <label for="genre">Genre:</label>
            <input class="form-control" id="genre" required v-model="book.genreName" name="genreName">
        </div>
        <div class="form-group">
            <label for="id">ID:</label>
            <input class="form-control" id="id" readonly v-model="book.id" name="id">
        </div>
        <button @click="saveBook" class="btn btn-success">Submit</button>
    </div>
</template>

<script>
    import BookDataService from "@/services/BookDataService";

    export default {
        name: "BookAdd",
        data() {
            return {
                book: {
                    id: 0,
                    title: "",
                    authorName: "",
                    genreName: ""
                },
            };
        },
        methods: {
            saveBook() {
                let data = {
                    id: this.book.id,
                    title: this.book.title,
                    genreName: this.book.genreName,
                    authorName: this.book.authorName
                };
                BookDataService.create(data)
                    .then(response => {
                        this.book.id = response.data.id;
                        console.log(response.data);
                    })
                    .catch(e => {
                        console.log(e);
                    });
                this.$router.go(-1);
            }
        }
    };
</script>
<style>
    .submit-form {
        max-width: 300px;
        margin: auto;
    }
</style>
