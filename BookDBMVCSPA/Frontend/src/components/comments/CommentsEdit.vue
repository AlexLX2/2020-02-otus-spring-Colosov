<template>
    <div class="edit-form">
        <h4>Comment</h4>
        <form>
            <div class="form-group">
                <label for="text">Text</label>
                <input type="text" class="form-control" id="text" v-model="currentComment.text"/>
            </div>
            <div class="form-group">
                <label for="id">Id</label>
                <input type="text" readonly class="form-control" id="id" v-model="currentComment.id"/>
            </div>
        </form>
        <button type="submit" class="btn btn-success" @click="updateComment">Update</button>
        <button class="btn badge-danger mr-2" @click="deleteComment">Delete</button>
    </div>
</template>

<script>
    import CommentDataService from "../../services/CommentDataService";

    export default {
        name: "CommentEdit",
        data() {
            return {
                currentComment: null
            };
        },
        methods: {
            getComment(bookId, commentId) {
                CommentDataService.getComment(bookId, commentId)
                    .then(response => {
                        this.currentComment = response.data;
                        console.log(response.data);
                    })
                    .catch(e => {
                        console.log(e);
                    })
            },
            updateComment() {
                CommentDataService.update(this.currentComment)
                    .then(response => {
                        console.log(response.data);
                    })
                    .catch(e => {
                        console.log(e);
                    });
                this.$router.go(-1);
            },
            deleteComment() {
                CommentDataService.delete(this.currentComment)
                    .then(response => {
                        console.log(response.data);
                        this.$router.go(-1);
                    })
                    .catch(e => {
                        console.log(e);
                    });
            }
        },
        mounted() {
            this.getComment(this.$route.params.id, this.$route.params.cid);
        }
    };
</script>

<style>
    .edit-form {
        max-width: 300px;
        margin: auto;
    }
</style>
