package com.example.hacknplanstat2.repository

import androidx.lifecycle.Transformations.map
import com.example.hacknplanstat2.httpClient
import com.example.hacknplanstat2.json
import com.example.hacknplanstat2.model.*
import com.example.hacknplanstat2.sourceUrl
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.serialization.decodeFromString
import org.koin.core.component.KoinComponent




class OnlineRepository : Repository, KoinComponent {

    private val client = httpClient

    private var apiKey : String = ""

    override lateinit var user : User
    override lateinit var project : Project


    override suspend fun checkApiKey(apiKey: String): Boolean {
        val response : HttpResponse = client.get("${sourceUrl}/v0/users/me") {
            method = HttpMethod.Get
            headers {
                append(HttpHeaders.Authorization, "ApiKey $apiKey")
            }
        }


        if(response.status.isSuccess()){
            this.apiKey = apiKey
            user = json.decodeFromString(response.readText())
            println("deserialized")
        }

        println("finished request")
        return response.status.isSuccess();
    }


    override suspend fun getCategories(): List<Category> {

        val rawCategories: List<RawCategory> =
            client.get("${sourceUrl}/v0/projects/${project.id}/categories") {
                method = HttpMethod.Get
                headers {
                    append(HttpHeaders.Authorization, "ApiKey $apiKey")
                }
            }

        return rawCategories.map { rawCategory -> rawCategory.toCategory() }
    }

    override suspend fun getWorkItemsByCategory(category: Category): Map<TaskSize, List<Task>>{
        val page : TaskPage = client.get("${sourceUrl}/v0/projects/${project.id}/workitems?categoryId=${category.categoryId}&stageId=4") {
            method = HttpMethod.Get
            headers {
                append(HttpHeaders.Authorization, "ApiKey $apiKey")
            }
        }
        val tasks = page.items

        val xs = tasks.filter { task -> task.tags.contains(Tag("XS"))}
        val s = tasks.filter { task -> task.tags.contains(Tag("S"))}
        val m = tasks.filter { task -> task.tags.contains(Tag("M"))}
        val l = tasks.filter { task -> task.tags.contains(Tag("L"))}
        val xl = tasks.filter { task -> task.tags.contains(Tag("XL"))}

        return mapOf(TaskSize.XS to xs, TaskSize.S to s, TaskSize.M to m, TaskSize.L to l, TaskSize.XL to xl);
    }

    override suspend fun getProjects(): List<Project> {

        return client.get("${sourceUrl}/v0/projects") {
            method = HttpMethod.Get
            headers {
                append(HttpHeaders.Authorization, "ApiKey $apiKey")
            }
        }
    }

    override suspend fun openProject(projectId : Int){
        project = client.get("${sourceUrl}/v0/projects/$projectId") {
            method = HttpMethod.Get
            headers {
                append(HttpHeaders.Authorization, "ApiKey $apiKey")
            }
        }
    }


}