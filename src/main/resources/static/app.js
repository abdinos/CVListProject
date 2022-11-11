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
        this.getActivities();
        this.getCvList();
    },

    methods: {
        // Place pour les futures méthodes
        login: function (){
            axios.post("http://localhost:8081/users/login",{
                data:{

                }
                },
                {
                    headers: {
                        'Accept': "application/json",
                        'Content-Type': 'application/json'
                    },
                    //body: JSON.stringify(this.user)
                })
                .then(data => console.log(data))
                .catch(err => console.log(">>>>>>>>>>>>>>",err))
            //console.log("btn login pressed ")
        },
        getActivities: function (){
            axios.get("http://localhost:8081/api/activities")
                .then(r => {
                    console.log("get activities done");
                    this.activities = r.data}
                )
        },
        getCvList: function (){
            axios.get("http://localhost:8081/api/cvList")
                .then(r => {
                    console.log("get cvList done");
                    this.cvList = r.data}
                )
        }
    }
}
Vue.createApp(myApp).mount('#myApp');