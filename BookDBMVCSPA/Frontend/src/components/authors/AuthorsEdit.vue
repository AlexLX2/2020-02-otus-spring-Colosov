<template>
    <div v-if="currentAuthor" class="edit-form">
        <h4>Author</h4>
        <form>
            <div class="form-group">
                <label for="name">Name</label>
                <input type="text" class="form-control" id="name" v-model="currentAuthor.name"/>
            </div>
            <div class="form-group">
                <label for="id">Id</label>
                <input type="text" class="form-control" id="id" v-model="currentAuthor.id"/>
            </div>
        </form>
        <button type="submit" class="btn btn-success" @click="updateAuthor">Update</button>
        <button class="btn badge-danger mr-2" @click="deleteAuthor">Delete</button>
    </div>
    <div v-else>
        <br/>
        <p>Please click on an Author...</p>
    </div>
</template>

<script>
    import AuthorDataService from "@/services/AuthorDataService";

    export default {
        name: "AuthorEdit",
        data() {
            return {
                currentAuthor: null
            };
        },
        methods: {
            getAuthor(id) {
                AuthorDataService.get(id)
                    .then(response => {
                        this.currentAuthor = response.data;
                        console.log(response.data);
                    })
                    .catch(e => {
                        console.log(e);
                    })
            },
            updateAuthor() {
                AuthorDataService.update(this.currentAuthor)
                    .then(response => {
                        console.log(response.data);
                    })
                    .catch(e => {
                        console.log(e);
                    });
                this.$router.go(-1);
            },
            deleteAuthor() {
                AuthorDataService.delete(this.currentAuthor.id)
                    .then(response => {
                        console.log(response.data);
                        this.$router.push("/authors/");
                    })
                    .catch(e => {
                        console.log(e.message);
                    });
            }
        },
        mounted() {
            this.getAuthor(this.$route.params.id);
        }
    };
</script>

<style>
    .edit-form {
        max-width: 300px;
        margin: auto;
    }
</style>
