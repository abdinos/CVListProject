const myApp = {

    // Préparation des données
    data() {
        console.log("data");
        return {
            activities: [],
            cvList: [],
            cv: null,
            axios: null,
            router: null,
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
        login:  function (){
            console.log('start>>>>>>>btn login pressed ! ! ! ')
            this.axios.post("users/login",
                {
                  data:{
                      'username': this.user.username,
                      'password': this.user.password
                  }
                }
                ,{
                headers:{
                    'Authorization': `Bearer ${this.token}`
                }
            }).then(r => {
                console.log(r.data)
            })
                .catch(err => console.log("errrrrr"))
            // axios.defaults.headers.common['Authorization'] = `Authorization:Bearer ${r.data.token}`
            // localStorage.setItem('userInfo', JSON.stringify(r))
            console.log('end<<<<<<btn login pressed ! ! ! ')

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
        createCV: function (){
            console.log("creating cv")
            this.axios.post("api/cv")
                .then(r=> {
                    console.log(r.data)
                    this.getCvList()
                    //this.cvList.add(r.data)
                })
        }

    }
}
Vue.createApp(myApp).mount('#myApp');