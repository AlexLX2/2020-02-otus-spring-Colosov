<template>
    <div class="list row">
        <div class="col-md-6">
            <h4>Authors List</h4>
            <ul class="list-group">
                <li class="list-group-item" :class="{ active: id == currentId}"
                    v-for="(author, id) in authors"
                    :key="id"
                    @click="setActiveAuthor(author, id)">
                    {{ author.name }}
                </li>
            </ul>
            <div align="center">
                <button class="btn btn-success" @click="createAuthor">
                    Add new
                </button>
            </div>
        </div>
        <div class="col-md-6">
            <div v-if="currentAuthor">
                <h4>Author</h4>
                <div>
                    <label><strong>Name: </strong></label> {{ currentAuthor.name }}
                </div>
                <div>
                    <label><strong>ID: </strong></label> {{ currentAuthor.id }}
                </div>
                <button @click="editAuthor" class="btn btn-success">Edit</button>
            </div>
            <div v-else>
                <br/>
                <p>Please click on author...</p>
            </div>

        </div>
    </div>
</template>

<script>
    import AuthorDataService from "@/services/AuthorDataService";
    import router from "@/router";

    export default {
        name: "AuthorsList",
        data() {
            return {
                authors: [],
                currentAuthor: null,
                currentId: -1,
                name: ""
            };
        },
        methods: {
            retreiveAuthors() {
                AuthorDataService.getAll()
                    .then(response => {
                        this.authors = response.data;
                        console.log(response.data);
                    })
                    .catch(e => {
                        console.log(e);
                    });
            },
            setActiveAuthor(author, id) {
                this.currentAuthor = author;
                this.currentId = id;
            },
            createAuthor() {
                router.push("/authors/add");
            },
            editAuthor() {
                router.push("/authors/" + this.currentAuthor.id);
            }
        },
        mounted() {
            this.retreiveAuthors();
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