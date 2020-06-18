<template>
    <div class="submit-form">
        <div class="form-group">
            <label for="text">Comment text:</label>
            <input type="text" class="form-control" id="text" required v-model="comment.text" name="text"/>
        </div>
        <div class="form-group">
            <label for="id">ID:</label>
            <input class="form-control" id="id" readonly v-model="comment.id" name="id">
        </div>
        <button @click="saveComment" class="btn btn-success">Submit</button>
    </div>
</template>

<script>
    import CommentDataService from "../../services/CommentDataService";

    export default {
        name: "CommentAdd",
        data() {
            return {
                comment: {
                    id: -1,
                    text: "",
                    bookId: this.$route.params.id
                },
            };
        },
        methods: {
            saveComment() {
                let data = {
                    text: this.comment.text,
                    id: this.comment.id,
                    bookId: this.$route.params.id
                };
                CommentDataService.create(data)
                    .then(response => {
                        this.comment.id = response.data.id;
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
