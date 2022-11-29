const profile = {

    // Préparation des données
    data() {
        console.log("data");
        return {
            axios: null,
            router: null,
        }
    },

    // Mise en place de l'application
    mounted() {
        console.log("Mounted ");
        this.axios = axios.create({
            baseURL: 'http://localhost:8081/',
            headers: { 'Content-Type': 'application/json' },
        });

    },

    methods: {
        // Place pour les futures méthodes


    }
}
Vue.createApp(profile).mount('#profile');