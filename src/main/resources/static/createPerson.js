const createPerson = {

    // Préparation des données
    data() {
        console.log("data");
        return {
            axios: null,
            router: null,
            username: null,
            newUser: {},
            user:{},
            cv: null,
            errors: [],
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

    },

    methods: {

        addPerson: function(newPerson) {
            this.axios.post('api/persons', newPerson ,{
                headers: {
                    Authorization: 'Bearer ' + this.token,
                }
            })
                .then(errors => {
                    console.log("new Person added: ", newPerson);
                    this.errors = errors.data;
                    location.href= "/profile"
                });
        },


    },
}
Vue.createApp(createPerson).mount('#createPerson');