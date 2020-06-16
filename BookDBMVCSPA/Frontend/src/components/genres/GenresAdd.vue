<template>
    <div class="submit-form">
        <div class="form-group">
            <label for="name">Genre name:</label>
            <input type="text" class="form-control" id="name" required v-model="genre.name" name="name"/>
        </div>
        <div class="form-group">
            <label for="id">ID:</label>
            <input class="form-control" id="id" readonly v-model="genre.id" name="id">
        </div>
        <button @click="saveGenre" class="btn btn-success">Submit</button>
    </div>
</template>

<script>
    import GenreDataService from "@/services/GenreDataService";

    export default {
        name: "GenreAdd",
        data() {
            return {
                genre: {
                    id: null,
                    name: ""
                },
            };
        },
        methods: {
            saveGenre() {
                let data = {
                    name: this.genre.name,
                    id: this.genre.id
                };
                GenreDataService.create(data)
                    .then(response => {
                        this.genre.id = response.data.id;
                        console.log(response.data);
                    })
                    .catch(e => {
                        console.log(e);
                    });
                this.$router.push("/genres");
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
