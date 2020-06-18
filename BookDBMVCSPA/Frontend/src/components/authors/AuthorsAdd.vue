<template>
    <div class="submit-form">
        <div class="form-group">
            <label for="name">Author name:</label>
            <input type="text" class="form-control" id="name" required v-model="author.name" name="name"/>
        </div>
        <div class="form-group">
            <label for="id">ID:</label>
            <input class="form-control" id="id" readonly v-model="author.id" name="id">
        </div>
        <button @click="saveAuthor" class="btn btn-success">Submit</button>
    </div>
</template>

<script>
    import AuthorDataService from "@/services/AuthorDataService";

    export default {
        name: "AuthorAdd",
        data() {
            return {
                author: {
                    id: null,
                    name: ""
                },
            };
        },
        methods: {
            saveAuthor() {
                let data = {
                    name: this.author.name,
                    id: this.author.id
                };
                AuthorDataService.create(data)
                    .then(response => {
                        this.author.id = response.data.id;
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