const profile = {

    // Préparation des données
    data() {
        console.log("data");
        return {
            axios: null,
            router: null,
            username: null,
            user: {},
            token: null,
            isLoggedIn: false,
            isLoggedOut: false,
        }
    },

    // Mise en place de l'application
    mounted() {
        console.log("Mounted ");
        this.axios = axios.create({
            baseURL: 'http://localhost:8081/',
            headers: { 'Content-Type': 'application/json' },
        });
        this.getCurrentUser()
    },

    methods: {
        // Place pour les futures méthodes
        getCurrentUser: function (){
            this.username = localStorage.getItem('USERNAME')
            console.log(this.username)
            if (this.username != null){
                this.isLoggedIn = true
            }
        },
        logoutUser: async function(){
            const res = await this.axios.get('users/logout')
            console.log(res)
            localStorage.removeItem('TOKEN')
            localStorage.removeItem('USERNAME')
            this.isLoggedOut = true
            console.log(this.isLoggedOut)

        },


    }
}
Vue.createApp(profile).mount('#profile');