package `in`.palashbauri.writefreelykt.jsonobjs

import kotlinx.serialization.Serializable


@Serializable
data class RetrivedSinglePostObj(val code: Int, val data: PostObj)

@Serializable
data class RenderMarkdownDataObj(val body: String)

@Serializable
data class RenderMarkdownObj(val code: Int, val data: RenderMarkdownDataObj)

@Serializable
data class SendToRenderMarkdownObj(val raw_body: String, val collection_url: String)

@Serializable
data class AllPostsObj(val code: Int, val data: Array<PostObj>) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as AllPostsObj

        if (code != other.code) return false
        if (!data.contentEquals(other.data)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = code
        result = 31 * result + data.contentHashCode()
        return result
    }
}

@Serializable
data class PostObj(
    val id: String,
    val slug: String,
    val appearance: String,
    val rtl: Boolean,
    val language: String,
    val created: String,
    val updated: String,
    val title: String,
    val body: String,
    val tags: Array<String>,
    val views: Int,
    val collection: BlogCollection,

    ) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as PostObj

        if (id != other.id) return false
        if (slug != other.slug) return false
        if (appearance != other.appearance) return false
        if (rtl != other.rtl) return false
        if (language != other.language) return false
        if (created != other.created) return false
        if (updated != other.updated) return false
        if (title != other.title) return false
        if (body != other.body) return false
        if (!tags.contentEquals(other.tags)) return false
        if (views != other.views) return false
        if (collection != other.collection) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + slug.hashCode()
        result = 31 * result + appearance.hashCode()
        result = 31 * result + rtl.hashCode()
        result = 31 * result + language.hashCode()
        result = 31 * result + created.hashCode()
        result = 31 * result + updated.hashCode()
        result = 31 * result + title.hashCode()
        result = 31 * result + body.hashCode()
        result = 31 * result + tags.contentHashCode()
        result = 31 * result + views
        result = 31 * result + collection.hashCode()
        return result
    }
}

@Serializable
data class UpdatePostObj(
    val token: String,
    val body: String,
    val title: String,
    val font: String,
    val lang: String,
    val rtl: Boolean
)

@Serializable
data class PublishPostObj(
    val body: String,
    val title: String,
    val font: String,
    val lang: String,
    val rtl: Boolean,
    val created: String,
    // Cross post not yet implemented
)

@Serializable
data class UnpublishPostObj(
    val token: String,
    val body: String = ""
)

@Serializable
data class ClaimPostObj(
    val id: String,
    val token: String
)