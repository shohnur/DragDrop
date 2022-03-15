package uz.task.models

data class ModelTwo(var name: String) : CommonModel() {
    override fun getType(): Int = 2
}
