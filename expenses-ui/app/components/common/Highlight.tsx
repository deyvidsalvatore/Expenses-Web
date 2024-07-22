import { StatusState } from "@/app/utils/types";

export default function Highlight({ state }: { state: StatusState }): JSX.Element {

    const colorMappings: Record<StatusState, { backgroundColor: string, textColor: string }> = {
        "IN_REVIEW": { backgroundColor: "bg-yellow-100", textColor: "text-yellow-700" },
        "APPROVED": { backgroundColor: "bg-green-100", textColor: "text-green-700" },
        "REJECTED": { backgroundColor: "bg-red-100", textColor: "text-red-700" }
    };

    const { backgroundColor, textColor } = colorMappings[state] || colorMappings["REJECTED"];

    return (
        <div className={`rounded ${backgroundColor} ${textColor} text-center p-1`}>
            {state}
        </div>
    );
}
