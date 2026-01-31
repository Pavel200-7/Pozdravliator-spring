import HomeView from '@/views/HomeView.vue'
import FriendsView from '@/views/friends/FriendsView.vue'
import CreateFriendView from '@/views/friends/CreateFriendView.vue'
import FriendView from '@/views/friends/FriendView.vue'
import SubscribersViev from '@/views/subscribers/SubscribersViev.vue'
import SubscriberViev from '@/views/subscribers/SubscriberViev.vue'

import { createRouter, createWebHistory } from 'vue-router'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: "/",
      name: "home",
      component: HomeView
    },
    {
      path: "/friends",
      name: "friends",
      component: FriendsView
    },
    {
      path: "/create_friend",
      name: "create_friend",
      component: CreateFriendView
    },
    {
    path: '/friends/:id',
    name: 'friend_detail',
    component: FriendView,
    props: true
    },
    {
      path: "/subscribers",
      name: "subscribers",
      component: SubscribersViev
    },
    {
    path: '/subscribers/:id',
    name: 'subscriber_detail',
    component: SubscriberViev,
    props: true
    }
  ],
})

export default router
