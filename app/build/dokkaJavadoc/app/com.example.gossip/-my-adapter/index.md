[app](../../index.md) / [com.example.gossip](../index.md) / [MyAdapter](./index.md)

# MyAdapter

`class MyAdapter : Adapter<`[`MyAdapter.MyViewHolder`](-my-view-holder/index.md)`>`

Recyclerview. Used to display a group of items

This class is mainly taken from the API in kotlin. It is used to display information is the
specified textboxes.

### Types

| Name | Summary |
|---|---|
| [MyViewHolder](-my-view-holder/index.md) | `class MyViewHolder : ViewHolder`<br>A viewholder |

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `MyAdapter(userList: `[`MutableList`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-list/index.html)`<`[`Users`](../-users/index.md)`>)`<br>Recyclerview. Used to display a group of items |

### Functions

| Name | Summary |
|---|---|
| [getItemCount](get-item-count.md) | `fun getItemCount(): `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [onBindViewHolder](on-bind-view-holder.md) | `fun onBindViewHolder(holder: `[`MyAdapter.MyViewHolder`](-my-view-holder/index.md)`, position: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [onCreateViewHolder](on-create-view-holder.md) | `fun onCreateViewHolder(parent: ViewGroup, viewType: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`MyAdapter.MyViewHolder`](-my-view-holder/index.md) |
