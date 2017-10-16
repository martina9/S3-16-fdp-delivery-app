var app = angular.module('fdpApp',['ui.router','ngStorage']);

app.constant('urls', {
    BASE: 'http://localhost:8080/FastDeliveryPizza',
    PRODUCT_SERVICE_API : 'http://localhost:8089/FastDeliveryPizza/api/products/',
    RESTAURANT_SERVICE_API : 'http://localhost:8089/FastDeliveryPizza/api/restaurants/',
    CATEGORIES_SERVICE_API : 'http://localhost:8089/FastDeliveryPizza/api/products/categories/',
    ORDER_SERVICE_API : 'http://localhost:8089/FastDeliveryPizza/api/orders/user/',
    USER_ORDER_SERVICE_API : 'http://localhost:8089/FastDeliveryPizza/api/orders/user/',
    RESTAURANT_ORDER_SERVICE_API : 'http://localhost:8089/FastDeliveryPizza/api/orders/restaurant/',
    PRODUCT_RESTAURANT_PRODUCT_SERVICE_API : 'http://localhost:8089/FastDeliveryPizza/api/products/restaurant/product/',
    PRODUCT_RESTAURANT_SERVICE_API : 'http://localhost:8089/FastDeliveryPizza/api/products/restaurant/',
    RESTAURANT_ID : 1
    // RESTAURANT_SERVICE_API : 'http://localhost:8089/FastDeliveryPizza/api/restaurants/',
});


app.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
    $stateProvider
        .state('product', {
                        url: '/product',
                        templateUrl: 'partials/listproduct',
                        controller:'ProductController',
                        controllerAs:'ctrl',
                        resolve: {
                            categories:function ($q, ProductService) {
                                console.log('Load all categories');
                                var deferred = $q.defer();
                                ProductService.loadCategories().then(deferred.resolve, deferred.resolve);
                                return deferred.promise;
                            },
                            products: function ($q, ProductService) {
                                console.log('Load all products');
                                var deferred = $q.defer();
                                ProductService.loadAllProducts().then(deferred.resolve, deferred.resolve);
                                return deferred.promise;
                            }
                        }
                    })
        .state('restaurant', {
            url: '/restaurant',
            templateUrl: 'partials/listrestaurant',
            controller:'RestaurantController',
            controllerAs:'rctrl',
            resolve: {
                restaurants:function ($q, ProductService) {
                    console.log('Load all restaurants');
                    var deferred = $q.defer();
                    RestaurantService.loadAllRestaurants().then(deferred.resolve, deferred.resolve);
                    return deferred.promise;
                },
                products: function ($q, ProductService) {
                    console.log('Load all products');
                    var deferred = $q.defer();
                    ProductService.loadAllProducts().then(deferred.resolve, deferred.resolve);
                    return deferred.promise;
                }
            }
        })
    // route to show our basic form (/form)
        .state('productRestaurant', {
            url: '/productRestaurant',
            templateUrl: 'partials/listproductrestaurant',
            controller: 'ProductRestaurantController',
            controllerAs:'prctrl',
            resolve: {
                productRestaurant:function ($q, ProductRestaurantService) {
                    console.log('Load all ProductRestaurant');
                    var deferred = $q.defer();
                    ProductRestaurantService.loadAllProductRestaurants(1).then(deferred.resolve, deferred.resolve);
                    return deferred.promise;
                },
                products:function ($q, ProductRestaurantService) {
                    console.log('Load all Products');
                    var deferred = $q.defer();
                    ProductRestaurantService.loadAllProducts().then(deferred.resolve, deferred.resolve);
                    return deferred.promise;
                    }
            }
        })
        .state('userorder', {
            url: '/userorder',
            templateUrl: 'partials/userorder',
            controller: 'OrderController',
            controllerAs:'octrl',
            resolve: {
                restaurants:function ($q, OrderService) {
                    console.log('Load all restaurants');
                    var deferred = $q.defer();
                    OrderService.loadAllRestaurants().then(deferred.resolve, deferred.resolve);
                    return deferred.promise;
                },
            }
        })
        // nested states
        // each of these sections will have their own view
        // url will be nested (/form/profile)
        .state('registration.user', {
            url: '/user',
            templateUrl: 'modules/registration/views/registration-user.html'
        })
        // nested states
        // each of these sections will have their own view
        // url will be nested (/form/profile)
        .state('registration.profile', {
            url: '/profile',
            templateUrl: 'modules/registration/views/registration-profile.html'
        })
        // url will be /form/deparment
        .state('registration.department', {
            url: '/deparments',
            templateUrl: 'modules/registration/views/registration-department.html'
        })
        .state('home', {
            url: '/',
            templateUrl: 'modules/home/views/home.html',
            controller: 'HomeController'
        })
        .state('login', {
            url: '/login',
            templateUrl: 'modules/authentication/views/login.html',
            controller: 'LoginController'
        });
    // catch all route
    // send users to the form page
    // $urlRouterProvider.otherwise('/login');
}]);
//
//
//
// app.config(['$stateProvider', '$urlRouterProvider',
//     function($stateProvider, $urlRouterProvider) {
//
//         $stateProvider
//             .state('home', {
//                 url: '/',
//                 templateUrl: 'partials/list',
//                 controller:'ProductController',
//                 controllerAs:'ctrl',
//                 resolve: {
//                     categories:function ($q, ProductService) {
//                         console.log('Load all categories');
//                         var deferred = $q.defer();
//                         ProductService.loadCategories().then(deferred.resolve, deferred.resolve);
//                         return deferred.promise;
//                     },
//                     products: function ($q, ProductService) {
//                         console.log('Load all products');
//                         var deferred = $q.defer();
//                         ProductService.loadAllProducts().then(deferred.resolve, deferred.resolve);
//                         return deferred.promise;
//                     }
//                 }
//             });
//         $urlRouterProvider.otherwise('/');
//     }]);

