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
            editable: null,
            errors: [],
            isAddActivity: false,
            newActivity: null,
            userInfo: [],
            showInfo: false,

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
        this.getCurrentUserInfo();
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
        getCurrentUserInfo: function (){
            let token = this.token;
            this.axios.get("api/userInfo",{
                headers: {
                    Authorization: 'Bearer ' + token,
                }
            })
                .then(r => {
                    console.log("get activities done");
                    this.userInfo = r.data}
                )
        },
        setShowUserInfo: function(status) {
            this.showInfo = status;
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
        editCV: async function(cv) {
            console.log("CV with id " + cv.id + " is set to be modified");
            this.editable = cv;
        },
        submitCV: function(cv) {
            let token = this.token;
            this.axios.put('api/profileCv/' + cv.id, cv, {
                headers: {
                    Authorization: 'Bearer ' + token,
                }
            })
                .then(errors => {
                    this.errors = errors.data;
                    this.getCvActivities();
                    this.getCurrentUserCv();
                });
        },
        clearEditable: function() {
            this.editable = null;
        },
        addActivity: function(newActivity) {
            let token = this.token
            console.log('add activity btn')
            this.axios.post('api/profileActivities', newActivity, {
                headers: {
                    Authorization: 'Bearer ' + token,
                }
            })
                .then(errors => {
                    console.log("new activity added: ", newActivity);
                    this.errors = errors.data;
                    this.getCvActivities();
                    this.getCurrentUserCv();
                });
        },
        setAddActivity: function(status) {
            this.isAddActivity = status;
            this.newActivity = {};
        },



    },
}
Vue.createApp(profile).mount('#profile');