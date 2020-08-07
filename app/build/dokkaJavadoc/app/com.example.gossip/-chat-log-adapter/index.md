[app](../../index.md) / [com.example.gossip](../index.md) / [ChatLogAdapter](./index.md)

# ChatLogAdapter

`class ChatLogAdapter : Adapter<`[`ChatLogAdapter.ChatLogViewHolder`](-chat-log-view-holder/index.md)`>`

Recyclerview. Used to display a group of items efficiently

### Types

| Name | Summary |
|---|---|
| [ChatLogViewHolder](-chat-log-view-holder/index.md) | `class ChatLogViewHolder : ViewHolder` |

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `ChatLogAdapter(chat: `[`MutableList`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-list/index.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>)`<br>Recyclerview. Used to display a group of items efficiently |

### Functions

| Name | Summary |
|---|---|
| [getItemCount](get-item-count.md) | `fun getItemCount(): `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [onBindViewHolder](on-bind-view-holder.md) | `fun onBindViewHolder(holder: `[`ChatLogAdapter.ChatLogViewHolder`](-chat-log-view-holder/index.md)`, position: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [onCreateViewHolder](on-create-view-holder.md) | `fun onCreateViewHolder(parent: ViewGroup, viewType: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`ChatLogAdapter.ChatLogViewHolder`](-chat-log-view-holder/index.md) |
