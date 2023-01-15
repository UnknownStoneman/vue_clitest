import VueRouter from 'vue-router'
import Hello from '../components/HelloWorld.vue'
import Main from '../components/Main.vue'
import Login from '../components/Login.vue'

const originalPush = VueRouter.prototype.push
VueRouter.prototype.push = function push(location) {
    return originalPush.call(this, location).catch(err => err)
}
export default new VueRouter({
   

   routes: [
    {
        path:'/',
        component:Login
    },
    {
        path:'/hello',
        component:Hello
    },
    {
        path:'/main',
        component:Main
    },
    {
        path:'/login',
        component:Login
    }
   ]
})