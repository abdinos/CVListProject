const edit = {

    // Préparation des données
    data() {
        console.log("data");
        return {
            axios: null,
            router: null,
            errors: [],

            activities: [],
            cv: null,
            token: localStorage.getItem('TOKEN'),
        }
    },


    // Mise en place de l'application
    mounted() {
        console.log("Mounted ");
        this.axios = axios.create({
            baseURL: 'http://localhost:8081/',
            headers: { 'Content-Type': 'application/json', },
        });
        this.getCurrentUserCv();
        this.getCvActivities();
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


    },
}
Vue.createApp(edit).mount('#edit');