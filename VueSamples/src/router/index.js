import Vue from 'vue'
import Router from 'vue-router'
import Index from "@/views/Index"
import Hello from "@/views/Hello"

Vue.use(Router)

export default new Router({
	mode: 'history',
	routes: [
	{
		path: '/',
		redirect: '/index'
	},
	{
		path: '/index',
		component: Index
	},
	{
		path: '/samples/hello',
		component: Hello
	}
	]
})
