<template>
    <div id="app">
        <nav class="navbar navbar-expand navbar-dark bg-dark">
            <a href="#" class="navbar-brand" id="navbarNavDrop">Alex Kols</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavDropdown"
                    aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNavDropdown">
                <uL class="navbar-nav">
                    <li class="nav-item">
                        <a class="nav-link" href="/authors"> Authors </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/books"> Books </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/genres"> Genres </a>
                    </li>
                </uL>
            </div>
        </nav>

        <div class="container mt-3">
            <router-view/>
        </div>
    </div>
</template>

<script>
    export default {
        name: 'app'
    }
</script>
