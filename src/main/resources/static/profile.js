const profile = {

    // Préparation des données
    data() {
        console.log("data");
        return {
            axios: null,
            router: null,
            username: null,
            user: {},
            activities: [],
            cv: null,
            token: localStorage.getItem('TOKEN'),
            isLoggedIn: false,
            isLoggedOut: false,

        }
    },


    // Mise en place de l'application
    mounted() {
        console.log("Mounted ");
        this.axios = axios.create({
            baseURL: 'http://localhost:8081/',
            headers: { 'Content-Type': 'application/json', },
        });
        this.getCurrentUser();
        this.getCvActivities();
        this.getCurrentUserCv();
    },

    methods: {

        getCvActivities: function (){
            let token = this.token;
            this.axios.get("api/profileActivities",{
                headers: {
                    Authorization: 'Bearer ' + token,
                }
            })
                .then(r => {
                    console.log("get activities done");
                    this.activities = r.data}
                )
            },
        // Place pour les futures méthodes
        getCurrentUser: function (){
            this.username = localStorage.getItem('USERNAME')
            console.log(this.username)
            if (this.username != null){
                this.isLoggedIn = true
            }
        },
        getCurrentUserCv: function (){
            let token = this.token;
            this.axios.get("api/profileCv",{
                headers: {
                    Authorization: 'Bearer ' + token,
                }
            })
                .then(r => {
                    console.log("show cv done");
                    this.cv = r.data;
                    console.log(r.data);
                });


        },
        logoutUser: async function(){
            localStorage.removeItem('TOKEN')
            localStorage.removeItem('USERNAME')
            localStorage.clear()
            this.isLoggedOut = true
            window.location.href = '/login'
            console.log(this.isLoggedOut)

        },

        addMovie: function(newMovie) {
            this.axios.post('api/movies/', newMovie)
                .then(errors => {
                    console.log("new movie added: ", newMovie);
                    this.errors = errors.data;;
                });
        },


    },
}
Vue.createApp(profile).mount('#profile');