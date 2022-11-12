const myApp = {

    // Préparation des données
    data() {
        console.log("data");
        return {
            user: {
                username: "",
                password: ""
            },
            activities: [],
            cvList: [],
            cv: null,
            axios: null,
            router: null

        }
    },

    // Mise en place de l'application
    mounted() {
        console.log("Mounted ");
        this.axios = axios.create({
            baseURL: 'http://localhost:8081/',
            timeout: 1000,
            headers: { 'Content-Type': 'application/json' },
        });
        this.getActivities();
        this.getCvList();
        this.getCvActivities();
    },

    methods: {
        // Place pour les futures méthodes
        login: function (){
            this.axios.post("users/login", {
                username: this.user.username,
                password: this.user.password
            }).then(r =>{
                    console.log(r)
                    this.router.push("/app")
                })
        },
        getActivities: function (){
            this.axios.get("api/activities")
                .then(r => {
                    console.log("get activities done");
                    this.activities = r.data}
                )
        },
        getCvList: function (){
            this.axios.get("api/cvList")
                .then(r => {
                    console.log("get cvList done");
                    this.cvList = r.data}
                )
        },
        getCvActivities: function (id){
            this.axios.get("api/cv/"+id)
                .then(r => {
                    console.log("show cv"+id+" done");
                    this.cv = r.data;
                });


        },

    }
}
Vue.createApp(myApp).mount('#myApp');