const login = {

    // Préparation des données
    data() {
        console.log("data");
        return {
            axios: null,
            router: null,
            user: {},
            token: null,
            isLoggedIn: false,
        }
    },

    // Mise en place de l'application
    mounted() {
        console.log("Mounted ");
        this.axios = axios.create({
            baseURL: 'http://localhost:8081/users/',
            timeout: 1000,
            headers: { 'Content-Type': 'application/json' },
        });

    },

    methods: {
        // Place pour les futures méthodes
        login: async function(user){
            //const {data} = await axios.post('login', user)
            console.log("data")

        }

    }
}
Vue.createApp(login).mount('#login');