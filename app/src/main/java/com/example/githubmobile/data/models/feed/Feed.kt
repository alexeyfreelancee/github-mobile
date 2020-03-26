package com.example.githubmobile.data.models.feed

data class Author(val name: String?, val uri: String?)

data class Base(val feed: Feed?)

data class Content(val type: String?, val content: String?)

data class Entry697224958(val author: Author?, val link: Link?, val id: String?, val published: String?, val title: Title?, val updated: String?, val content: Content?)

data class Feed(val entry: List<Entry697224958>?, val link: List<Link48718750>?, val id: String?, val title: String?, val updated: String?)

data class Link(val rel: String?, val href: String?, val type: String?)

data class Link48718750(val rel: String?, val href: String?, val type: String?)

data class Title(val type: String?, val content: String?)
