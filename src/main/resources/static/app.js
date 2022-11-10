const myApp = {

    // Préparation des données
    data() {
        console.log("data");
        return {
            user: {
                login: "",
                password: ""
            },
            axios: null,
        }
    },

    // Mise en place de l'application
    mounted() {
        console.log("Mounted ");
        this.axios = axios.create({
            baseURL: 'http://localhost:8081/api/',
            timeout: 1000,
            headers: { 'Content-Type': 'application/json' },
        });
    },

    methods: {
        // Place pour les futures méthodes
        login: function (){
            // this.axios.post("/secu-users/login")
            //     .then(r => r.json())
            //     .then(data => console.log(data));
            console.log("logiiiiiiiiiiiiiiiiiin")

        }
    }
}
Vue.createApp(myApp).mount('#myApp');