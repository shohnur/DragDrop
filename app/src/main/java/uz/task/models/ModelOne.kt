package uz.task.models

data class ModelOne(var id: Int) : CommonModel() {
    override fun getType(): Int = 1
}