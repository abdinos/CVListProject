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
            username : "xxxx"
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
        authUser: async function(user){
            const {data} = await this.axios.post('users/loginUser', user)
            this.token = data
            console.log(this.token)
            localStorage.setItem('TOKEN',this.token)
            this.isLoggedIn = true
            await this.getUsername(this.token)
            window.location.href = "/profile"


        },
        getUsername: async function(token){
            const {data: username} = await this.axios.get('users/userInfo',{headers:{token}})
            console.log(username)
            if (username) {
                this.username = username
                console.log(this.username)
                localStorage.setItem("USERNAME", username)

            }
        }


    }
}
Vue.createApp(login).mount('#login');