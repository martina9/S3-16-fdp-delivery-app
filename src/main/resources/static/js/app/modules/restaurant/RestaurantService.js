'use strict';

angular.module('fdpApp').factory('RestaurantService',
    ['$localStorage', '$http', '$q', 'urls',
        function ($localStorage, $http, $q, urls) {

            var factory = {
                loadAllRestaurants: loadAllRestaurants,
                getAllRestaurants: getAllRestaurants,
                getRestaurant: getRestaurant,
                createRestaurant: createRestaurant,
                updateRestaurant: updateRestaurant,
                removeRestaurant: removeRestaurant,
                loadCategories: loadCategories,
                getAllCategories :getAllCategories
            };

            return factory;

            function loadCategories() {
                console.log('Fetching all categories');
                var deferred = $q.defer();
                $http.get(urls.CATEGORIES_SERVICE_API)
                    .then(
                        function (response) {
                            console.log('Fetched successfully all catgories');
                            $localStorage.categories = response.data;
                            deferred.resolve(response);
                        },
                        function (errResponse) {
                            console.error('Error while loading categories');
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }
            function loadAllRestaurants() {
                console.log('Fetching all restaurants');
                var deferred = $q.defer();
                $http.get(urls.RESTAURANT_SERVICE_API)
                    .then(
                        function (response) {
                            console.log('Fetched successfully all restaurants');
                            $localStorage.restaurants = response.data;
                            deferred.resolve(response);
                        },
                        function (errResponse) {
                            console.error('Error while loading restaurants');
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            function getAllRestaurants(){
                console.log('Get all restaurant');
                return $localStorage.restaurants;
            }

            function getAllCategories(){
                console.log('GetAllCategories ');
                return $localStorage.categories;
            }
            function getRestaurant(id) {
                console.log('Fetching Restaurant with id :'+id);
                var deferred = $q.defer();
                $http.get(urls.RESTAURANT_SERVICE_API + id)
                    .then(
                        function (response) {
                            console.log('Fetched successfully Restaurant with id :'+id);
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            console.error('Error while loading restaurant with id :'+id);
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            function createRestaurant(restaurant) {
                console.log('Creating Restaurant');
                var deferred = $q.defer();
                $http.post(urls.RESTAURANT_SERVICE_API, restaurant)
                    .then(
                        function (response) {
                            loadAllRestaurants();
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                           console.error('Error while creating Restaurant : '+errResponse.data.errorMessage);
                           deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            function updateRestaurant(restaurant, id) {
                console.log('Updating Restaurant with id '+id);
                var deferred = $q.defer();
                $http.put(urls.RESTAURANT_SERVICE_API + id, restaurant)
                    .then(
                        function (response) {
                            loadAllRestaurants();
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            console.error('Error while updating Restaurant with id :'+id);
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            function removeRestaurant(id) {
                console.log('Removing Restaurant with id '+id);
                var deferred = $q.defer();
                $http.delete(urls.RESTAURANT_SERVICE_API + id)
                    .then(
                        function (response) {
                            loadAllRestaurants();
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            console.error('Error while removing Restaurant with id :'+id);
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

        }
    ]);