
var app = new Vue({
    el:"#app",
    data: {
        apiUrl:"http://localhost:8080/todo/", // apiUrl
        tasksList: {}, //任务列表
        tasksSum: 0,
        task: "", // 任务输入值
        joke: "点击笑一笑!!!", // 笑话
        uid: "", // 用户编号
        uname: "", // 用户名
        isAddUser : false, // 用户是否添加成功
        isRegistered: false, // 用户是否注册
        isLogin : false ,// 是否登录成功
    },
    methods: {
        remove(index) {
            console.log(index);
            var that = this;
            axios.get(that.apiUrl+"/delete?count="+index).then(function(response){
                // console.log(response);
                that.$options.methods.updateData(that);
            },function (err) {  })
        },
        add() {
            var that = this;
            if(that.task!=''){
                axios.get(that.apiUrl+"/tasks?type=AddTask&task="+that.task+"&tuid="+that.uid).then(function(response){
                // console.log(response)
                that.task = "";
                that.$options.methods.updateData(that);
            },function (err) {  })
            }else{
                alert("任务不能为空!!!")
            }
        },
        getJoke(){
            // console.log(this.joke);
            var that = this;
            axios.get("https://autumnfish.cn/api/joke").then(function(response){
                // console.log(response)
                // console.log(response.data);
                // console.log(this.joke);
                that.joke = response.data;
            },function (err) {  })
        },
        login(){
            this.isLogin = true;
        },
        getUserData(){
            // alert("ok");
            var that = this;
            if (that.uname == ''){
                alert("用户名不能为空！！！");
            } else {
                that.isLogin = false;
                axios.get(that.apiUrl+"/user?uname="+that.uname).then(function(response){
                    // console.log(response);
                    that.isRegistered = response.data.isRegistered;
                    that.isAddUser = response.data.isAddUser;
                    if (that.isRegistered){
                        that.uid = response.data.uid;
                        that.uname = response.data.uname;
                        // alert(that.uid);
                        console.log(that.uname);
                        console.log(that.uid);
                        that.$options.methods.updateData(that);
                    } else if (that.isAddUser){
                        alert("您未注册用户,以为您自动注册用户。")
                        axios.get(that.apiUrl+"/user?uname="+that.uname).then(function(response){
                            console.log(response);
                            that.uid = response.data.uid;
                            that.uname = response.data.uname;
                            that.isRegistered = true;
                            // console.log(that.uname);
                            // console.log(that.uid);
                            that.$options.methods.updateData(that);
                        },function (err) {  })
                    } else {
                        alert("未知错误！！！")
                    }
                },function (err) {})
            }
        },
        updateData(e){
            var that = e;
            // alert("ok");
            // let list = {};
            axios.get(that.apiUrl+"/tasks?type=QueryTask&tuid="+that.uid).then(function(response){
                // console.log(response);
                // console.log(response.data.tasks);
                that.tasksList = response.data.tasks;
                that.tasksSum = response.data.sum;
            },function (err) {  })
        },
        getTasks(){
            var that = this;
            // alert("ok");
            // let list = {};
            axios.get(that.apiUrl+"/tasks?type=QueryTask&tuid="+this.uid).then(function(response){
                // console.log(response);
                // console.log(response.data.tasks);
                that.tasksList = response.data.tasks;
                that.tasksSum = response.data.sum;
            },function (err) {  })
        }
    }
})
