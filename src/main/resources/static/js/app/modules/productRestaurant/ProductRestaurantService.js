'use strict';

angular.module('fdpApp').factory('ProductRestaurantService',
    ['$localStorage', '$http', '$q', 'urls',
        function ($localStorage, $http, $q, urls) {

            var factory = {
                loadAllProducts:loadAllProducts,
                getAllProducts:getAllProducts,
                loadAllProductRestaurants: loadAllProductRestaurants,
                getAllProductRestaurants: getAllProductRestaurants,
                getProductRestaurant: getProductRestaurant,
                createProductRestaurant: createProductRestaurant,
                updateProductRestaurant: updateProductRestaurant,
                removeProductRestaurant: removeProductRestaurant
            };

            return factory;

            function loadAllProducts() {
                console.log('Fetching all categories');
                var deferred = $q.defer();
                $http.get(urls.PRODUCT_SERVICE_API)
                    .then(
                        function (response) {
                            console.log('Fetched successfully all products');
                            $localStorage.products = response.data;
                            deferred.resolve(response);
                        },
                        function (errResponse) {
                            console.error('Error while loading products');
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            function loadAllProductRestaurants() {
                console.log('Fetching all productRestaurants');
                var deferred = $q.defer();
                $http.get(urls.PRODUCT_RESTAURANT_SERVICE_API+1)
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
                console.log('Get all productRestaurant');
                return $localStorage.productRestaurants;
            }

            function getAllProducts(){
                console.log('GetAllCategories ');
                return $localStorage.products;
            }

            function getProductRestaurant(id) {
                console.log('Fetching ProductRestaurant with id :'+id);
                var deferred = $q.defer();
                $http.get(urls.PRODUCT_RESTAURANT_PRODUCT_SERVICE_API + id)
                    .then(
                        function (response) {
                            console.log('Fetched successfully ProductRestaurant with id :'+id);
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            console.error('Error while loading productRestaurant with id :'+id);
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            function createProductRestaurant(productRestaurant) {
                console.log('Creating ProductRestaurant');
                var deferred = $q.defer();
                $http.post(urls.PRODUCT_RESTAURANT_PRODUCT_SERVICE_API, productRestaurant)
                    .then(
                        function (response) {
                            loadAllProductRestaurants();
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                           console.error('Error while creating ProductRestaurant : '+errResponse.data.errorMessage);
                           deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            function updateProductRestaurant(productRestaurant, id) {
                console.log('Updating ProductRestaurant with id '+id);
                var deferred = $q.defer();
                $http.put(urls.PRODUCT_RESTAURANT_PRODUCT_SERVICE_API + id, productRestaurant)
                    .then(
                        function (response) {
                            loadAllProductRestaurants();
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            console.error('Error while updating ProductRestaurant with id :'+id);
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            function removeProductRestaurant(id) {
                console.log('Removing ProductRestaurant with id '+id);
                var deferred = $q.defer();
                $http.delete(urls.PRODUCT_RESTAURANT_PRODUCT_SERVICE_API + id)
                    .then(
                        function (response) {
                            loadAllProductRestaurants();
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            console.error('Error while removing ProductRestaurant with id :'+id);
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

        }
    ]);