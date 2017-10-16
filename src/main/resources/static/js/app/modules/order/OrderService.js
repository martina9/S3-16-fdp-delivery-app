'use strict';

angular.module('fdpApp').factory('OrderService',
    ['$localStorage', '$http', '$q', 'urls',
        function ($localStorage, $http, $q, urls) {

            var factory = {
                getAllRestaurants:getAllRestaurants,
                loadAllRestaurants:loadAllRestaurants,
                loadAllProductRestaurants:loadAllProductRestaurants,
                loadAllRestaurantOrders: loadAllRestaurantOrders,
                loadAllUserOrders: loadAllUserOrders,
                getAllUserOrders: getAllUserOrders,
                getAllRestaurantOrders:getAllRestaurantOrders,
                getAllProductRestaurants:getAllProductRestaurants,
                getOrder: getOrder,
                createOrder: createOrder,
                updateOrder: updateOrder,
                removeOrder: removeOrder,
            };

            return factory;

            function loadAllRestaurants() {
                console.log('Fetching all userrestaurants');
                var deferred = $q.defer();
                $http.get(urls.RESTAURANT_SERVICE_API)
                    .then(
                        function (response) {
                            console.log('Fetched successfully all userrestaurants');
                            $localStorage.userrestaurants = response.data;
                            deferred.resolve(response);
                        },
                        function (errResponse) {
                            console.error('Error while loading userrestaurants');
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            function getAllRestaurants(){
                console.log('Get all restaurant');
                return $localStorage.userrestaurants;
            }

            function loadAllProductRestaurants(restaurantId) {
                console.log('Fetching all productRestaurants');
                var deferred = $q.defer();
                $http.get(urls.PRODUCT_RESTAURANT_SERVICE_API+restaurantId)
                    .then(
                        function (response) {
                            console.log('Fetched successfully all productRestaurants');
                            $localStorage.productRestaurants = response.data;
                            deferred.resolve(response);
                        },
                        function (errResponse) {
                            console.error('Error while loading productRestaurants');
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            function getAllProductRestaurants(){
                console.log('Get all restaurant' +$localStorage.productRestaurants);
                return $localStorage.productRestaurants;
            }

            function loadAllUserOrders() {
                console.log('Fetching all restaurant orders');
                var deferred = $q.defer();
                $http.get(urls.USER_ORDER_SERVICE_API+1)
                    .then(
                        function (response) {
                            console.log('Fetched successfully all orders');
                            $localStorage.userorders = response.data;
                            deferred.resolve(response);
                        },
                        function (errResponse) {
                            console.error('Error while loading orders');
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            function getAllUserOrders(){
                console.log('Get all user orders');
                return $localStorage.userorders;
            }

            function loadAllRestaurantOrders() {
                console.log('Fetching all restaurant orders');
                var deferred = $q.defer();
                $http.get(urls.RESTAURANT_ORDER_SERVICE_API)
                    .then(
                        function (response) {
                            console.log('Fetched successfully all orders');
                            $localStorage.restaurantorders = response.data;
                            deferred.resolve(response);
                        },
                        function (errResponse) {
                            console.error('Error while loading orders');
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            function getAllRestaurantOrders(){
                console.log('GetAllProducuts all orders');
                return $localStorage.restaurantorders;
            }

            function getOrder(id) {
                console.log('Fetching Order with id :'+id);
                var deferred = $q.defer();
                $http.get(urls.ORDER_SERVICE_API + id)
                    .then(
                        function (response) {
                            console.log('Fetched successfully Order with id :'+id);
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            console.error('Error while loading order with id :'+id);
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            function createOrder(order) {
                console.log('Creating Order');
                var deferred = $q.defer();
                $http.post(urls.ORDER_SERVICE_API, order)
                    .then(
                        function (response) {
                            loadAllUserOrders();
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                           console.error('Error while creating Order : '+errResponse.data.errorMessage);
                           deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            function updateOrder(order, id) {
                console.log('Updating Order with id '+id);
                var deferred = $q.defer();
                $http.put(urls.ORDER_SERVICE_API + id, order)
                    .then(
                        function (response) {
                            loadAllUserOrders();
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            console.error('Error while updating Order with id :'+id);
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            function removeOrder(id) {
                console.log('Removing Order with id '+id);
                var deferred = $q.defer();
                $http.delete(urls.ORDER_SERVICE_API + id)
                    .then(
                        function (response) {
                            loadAllUserOrders();
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            console.error('Error while removing Order with id :'+id);
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

        }
    ]);