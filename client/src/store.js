import Vue from 'vue'
import Vuex from 'vuex'
import ApiService from "@/services/ApiService";
import {ShoppingCart} from "@/models/ShoppingCart";

Vue.use(Vuex)

export const CART_STORAGE_KEY = "cart";

export default new Vuex.Store({
    state: {
        categories: [],
        selectedCategoryName: "",
        selectedCategoryBooks: [],
        featuredCategoryName: "",
        featuredCategoryBooks: [],
        cart: new ShoppingCart(),
        orderDetails: null
    },
    mutations: {
        SET_CATEGORIES(state, newCategories) {
            state.categories = newCategories;
        },
        SELECT_CATEGORY(state, selectedCategoryName) {
            state.selectedCategoryName = selectedCategoryName
        },
        SET_SELECTED_CATEGORY_BOOKS(state, selectedCategoryBooks) {
            state.selectedCategoryBooks = selectedCategoryBooks;
        },
        SELECT_FEATURED_CATEGORY(state, featuredCategoryName) {
            state.featuredCategoryName = featuredCategoryName
        },
        SET_FEATURED_CATEGORY_BOOKS(state, featuredCategoryBooks) {
            state.featuredCategoryBooks = featuredCategoryBooks;
        },
        ADD_TO_CART(state, book) {
            state.cart.addItem(book, 1);
            localStorage.setItem(CART_STORAGE_KEY, JSON.stringify(this.state.cart));
        },
        UPDATE_CART(state, { book, quantity }) {
            state.cart.update(book, quantity);
            localStorage.setItem(CART_STORAGE_KEY, JSON.stringify(this.state.cart));
        },
        CLEAR_CART(state) {
            state.cart.clear();
            localStorage.setItem(CART_STORAGE_KEY, JSON.stringify(this.state.cart));
        },
        SET_CART(state, shoppingCart) {
            localStorage.setItem(CART_STORAGE_KEY, JSON.stringify(shoppingCart));
            let newCart = new ShoppingCart();
            shoppingCart.items.forEach(item => {
                newCart.addItem(item.book, item.quantity);
            });
            state.cart = newCart;
        },
        CLEAR_ORDER_DETAILS(state) {
            state.orderDetails.clear();
        },
        SET_ORDER_DETAILS(state, orderDetails) {
            state.orderDetails = orderDetails;
        }
    },
    actions: {
        fetchCategories(context) {
            ApiService.fetchCategories()
                .then(categories => {
                    console.log("Data: " + categories);
                    context.commit('SET_CATEGORIES', categories);
                })
                .catch(reason => {
                    console.log("Error: " + reason);
                });
        },
        selectCategory(context, selectedCategoryName) {
            context.commit('SELECT_CATEGORY', selectedCategoryName);
        },
        fetchSelectedCategoryBooks(context) {
            return ApiService.fetchCategoryBooks(context.state.selectedCategoryName)
                .then(books => {
                    console.log("Data: " + books);
                    context.commit('SET_SELECTED_CATEGORY_BOOKS', books)
                })
                .catch(reason => {
                    console.log("Error: " + reason);
                    throw reason
                });
        },
        selectFeaturedCategory(context, featuredCategoryName) {
            context.commit('SELECT_FEATURED_CATEGORY', featuredCategoryName);
        },
        fetchFeaturedCategoryBooks(context) {
            ApiService.fetchCategoryBooks(context.state.featuredCategoryName)
            .then(books => {
                    console.log("Data: " + books);
                    context.commit('SET_FEATURED_CATEGORY_BOOKS', books);
                })
                .catch(reason => {
                    console.log("Error: ", reason);
                });
        },
        addToCart(context, book) {
            context.commit('ADD_TO_CART', book);
        },
        updateCart(context, book, quantity) {
            context.commit('UPDATE_CART', book, quantity);
        },
        clearCart(context) {
            context.commit('CLEAR_CART');
        },
        placeOrder({ commit, state }, customerForm) {
            return ApiService.placeOrder({
                cart: state.cart,
                customerForm: customerForm
            }).then(orderDetails => {
                commit('CLEAR_CART')
                commit('SET_ORDER_DETAILS', orderDetails);
            })
        },
        clearOrderDetails(context) {
            context.commit('CLEAR_ORDER_DETAILS');
        }
    }
});