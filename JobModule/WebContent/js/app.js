/**
 * 
 */

var app=angular.module('app',['ngRoute','ngCookies','ngFileUpload']);
app.config(function($routeProvider){
	$routeProvider
.when('/',{
	templateUrl:'home.html'
})

.when('/user_home',{
	templateUrl:'c_home/user_home.html',
    controller:"UserController"
    	})
    	
    	when('/event',{
	templateUrl:'c_upload/event.html',
    controller:"FileUploadController"
    	})
    	
    	
    	when('/about',{
	templateUrl:'c_about/about.html',
    controller:"AboutController"
    	})
    	
    	
    	when('/login',{
	templateUrl:'c_user/login.html',
    controller:"UserController"
    	})
    	
    	
    	when('/register',{
	templateUrl:'c_user/register.html',
    controller:"RegisterController"
    	})
    	
    	//to post job
    	
    	.when('/postJob', {
		controller : 'JobController',
		templateUrl : 'c_job/createJob.html'
	})

	// to view all the jobs
	.when('/getAllJobs', {
		controller : 'JobController',
		templateUrl : 'c_job/jobsTitles.html'
	})

	// to view the job detail of a job
	.when('/jobDetail/:jobId', {
		controller : 'JobDetailController',
		templateUrl : 'c_job/jobDetail.html'
	})
	
	//search for job
	.when('/searchJob',{
	templateUrl:'search_job.html'
})
    	


})