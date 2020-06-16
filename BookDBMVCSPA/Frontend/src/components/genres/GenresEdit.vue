<template>
    <div v-if="currentGenre" class="edit-form">
        <h4>Genre</h4>
        <form>
            <div class="form-group">
                <label for="name">Name</label>
                <input type="text" class="form-control" id="name" v-model="currentGenre.name"/>
            </div>
            <div class="form-group">
                <label for="id">Id</label>
                <input type="text" class="form-control" id="id" v-model="currentGenre.id"/>
            </div>
        </form>
        <button type="submit" class="btn btn-success" @click="updateGenre">Update</button>
        <button class="btn badge-danger mr-2" @click="deleteGenre">Delete</button>
    </div>
    <div v-else>
        <br/>
        <p>Please click on an Genre...</p>
    </div>
</template>

<script>
    import GenreDataService from "@/services/GenreDataService";

    export default {
        name: "GenreEdit",
        data() {
            return {
                currentGenre: null
            };
        },
        methods: {
            getGenre(id) {
                GenreDataService.get(id)
                    .then(response => {
                        this.currentGenre = response.data;
                        console.log(response.data);
                    })
                    .catch(e => {
                        console.log(e);
                    })
            },
            updateGenre() {
                GenreDataService.update(this.currentGenre)
                    .then(response => {
                        console.log(response.data);
                    })
                    .catch(e => {
                        console.log(e);
                    });
                this.$router.go(-1);
            },
            deleteGenre() {
                GenreDataService.delete(this.currentGenre.id)
                    .then(response => {
                        console.log(response.data);
                        this.$router.push("/genres/" + this.currentGenre.id);
                    })
                    .catch(e => {
                        console.log(e.message);
                    });
            }
        },
        mounted() {
            this.getGenre(this.$route.params.id);
        }
    };
</script>

<style>
    .edit-form {
        max-width: 300px;
        margin: auto;
    }
</style>
