<template>
    <div class="list row">
        <div class="col-md-6">
            <h4>Genres List</h4>
            <ul class="list-group">
                <li class="list-group-item" :class="{ active: id == currentId}"
                    v-for="(genre, id) in genres"
                    :key="id"
                    @click="setActivegenre(genre, id)">
                    {{ genre.name }}
                </li>
            </ul>
            <div align="center">
                <button class="btn btn-success" @click="creategenre">
                    Add new
                </button>
            </div>
        </div>
        <div class="col-md-6">
            <div v-if="currentgenre">
                <h4>Genre</h4>
                <div>
                    <label><strong>Name: </strong></label> {{ currentgenre.name }}
                </div>
                <div>
                    <label><strong>ID: </strong></label> {{ currentgenre.id }}
                </div>
                <button @click="editgenre" class="btn btn-success">Edit</button>
            </div>
            <div v-else>
                <br/>
                <p>Please click on genre...</p>
            </div>

        </div>
    </div>
</template>

<script>
    import GenreDataService from "@/services/GenreDataService";
    import router from "@/router";

    export default {
        name: "genresList",
        data() {
            return {
                genres: [],
                currentgenre: null,
                currentId: -1,
                name: ""
            };
        },
        methods: {
            retreivegenres() {
                GenreDataService.getAll()
                    .then(response => {
                        this.genres = response.data;
                        console.log(response.data);
                    })
                    .catch(e => {
                        console.log(e);
                    });
            },
            setActivegenre(genre, id) {
                this.currentgenre = genre;
                this.currentId = id;
            },
            creategenre() {
                router.push("/genres/add");
            },
            editgenre() {
                router.push("/genres/" + this.currentgenre.id);
            }
        },
        mounted() {
            this.retreivegenres();
        }
    }
</script>

<style>
    .list {
        text-align: left;
        max-width: 750px;
        margin: auto;
    }
</style>
